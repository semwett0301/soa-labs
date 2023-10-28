import { Additional, TableData } from "components";
import { PAGE_SIZE } from "configs";
import {
  AdditionalInfoSection,
  LoadContainer,
  MainContainer,
  TableSection,
} from "containers";
import { NotificationContext } from "context";
import React, {
  FC,
  Key,
  useCallback,
  useContext,
  useEffect,
  useMemo,
  useState,
} from "react";
import {
  useDeleteStudyGroupMutation,
  useGetStudyGroupMaxAdminQuery,
  useGetStudyGroupMinAdminQuery,
  useGetStudyGroupsCountedByNameQuery,
  useGetStudyGroupsQuery,
} from "services";
import { FormName, GetStudyGroupFilters } from "types";
import { formNameToFormOfEducation } from "utils";

import {
  usePostChangeEduFormMutation,
  usePostExpellAllStudentMutation,
} from "../services/isu";

export const AppPanel: FC = () => {
  const [currentPage, setCurrentPage] = useState(0);
  const [sort, setSort] = useState<string[]>([]);
  const [filters, setFilters] = useState<GetStudyGroupFilters>({});

  const studyGroupsQuery = useGetStudyGroupsQuery({
    page: currentPage,
    pageSize: PAGE_SIZE,
    sort,
    ...filters,
  });

  const maxAdminGroupQuery = useGetStudyGroupMaxAdminQuery(null);
  const minAdminGroupQuery = useGetStudyGroupMinAdminQuery(null);

  const groupsCountedByNameQuery = useGetStudyGroupsCountedByNameQuery(null);

  const [expellAll, expellAllMutation] = usePostExpellAllStudentMutation();
  const [deleteAll, deleteAllMutatation] = useDeleteStudyGroupMutation();
  const [changeForm, changeFormMutation] = usePostChangeEduFormMutation();

  const { openNotification } = useContext(NotificationContext);

  const onExpel = useCallback<(id: Key) => Promise<void>>(
    async (id) => {
      await expellAll(id);
    },
    [expellAll]
  );

  const onDelete = useCallback<(id: Key) => Promise<void>>(
    async (id) => {
      await deleteAll(id);
    },
    [deleteAll]
  );

  const onChangeForm = useCallback<(id: Key, form: FormName) => Promise<void>>(
    async (id, form) => {
      await changeForm({ form: formNameToFormOfEducation[form], id });
    },
    [changeForm]
  );

  const isError = useMemo<boolean>(
    () =>
      studyGroupsQuery.isError ||
      maxAdminGroupQuery.isError ||
      minAdminGroupQuery.isError ||
      groupsCountedByNameQuery.isError ||
      expellAllMutation.isError ||
      deleteAllMutatation.isError ||
      changeFormMutation.isError,
    [
      changeFormMutation.isError,
      deleteAllMutatation.isError,
      expellAllMutation.isError,
      groupsCountedByNameQuery.isError,
      maxAdminGroupQuery.isError,
      minAdminGroupQuery.isError,
      studyGroupsQuery.isError,
    ]
  );

  const isLoading = useMemo<boolean>(
    () =>
      studyGroupsQuery.isLoading ||
      maxAdminGroupQuery.isLoading ||
      minAdminGroupQuery.isLoading ||
      groupsCountedByNameQuery.isLoading ||
      expellAllMutation.isLoading ||
      deleteAllMutatation.isLoading ||
      changeFormMutation.isLoading,
    [
      changeFormMutation.isLoading,
      deleteAllMutatation.isLoading,
      expellAllMutation.isLoading,
      groupsCountedByNameQuery.isLoading,
      maxAdminGroupQuery.isLoading,
      minAdminGroupQuery.isLoading,
      studyGroupsQuery.isLoading,
    ]
  );

  useEffect(() => {
    if (isError) {
      openNotification("Некорректные данные");
    }
  }, [isError]);

  return (
    <MainContainer>
      <LoadContainer isLoading={isLoading}>
        <TableSection>
          <TableData
            currentPage={currentPage}
            setCurrentPage={setCurrentPage}
            setSort={setSort}
            totalPages={studyGroupsQuery.data?.totalPages}
            content={studyGroupsQuery.data?.content}
            onExpell={onExpel}
            onDelete={onDelete}
            onUpdateForm={onChangeForm}
            onFilter={setFilters}
          />
        </TableSection>
        <AdditionalInfoSection>
          <Additional
            title="Группа с макс. ростом админа"
            value={maxAdminGroupQuery.data?.name}
          />
          <Additional
            title="Группа с мин. ростом админа"
            value={minAdminGroupQuery.data?.name}
          />
          <Additional
            title="Количество элементов, сгруппированных по имени"
            value={
              Array.isArray(groupsCountedByNameQuery.data)
                ? groupsCountedByNameQuery.data?.reduce((acc, el) => {
                    acc += `${el.groupName} - ${el.count}\n`;
                    return acc;
                  }, "")
                : ""
            }
          />
        </AdditionalInfoSection>
      </LoadContainer>
    </MainContainer>
  );
};
