import { Header } from "atom/Header";
import { Additional, TableData } from "components";
import { PAGE_SIZE } from "configs";
import { Styles } from "configs/styles";
import {
  AdditionalInfoSection,
  LoadContainer,
  MainContainer,
  TableSection,
} from "containers";
import React, { FC, Key, useCallback, useState } from "react";
import {
  useDeleteStudyGroupMutation,
  useGetStudyGroupMaxAdminQuery,
  useGetStudyGroupMinAdminQuery,
  useGetStudyGroupsCountedByNameQuery,
  useGetStudyGroupsQuery,
} from "services";
import { FormName } from "types";

import {
  usePostChangeEduFormMutation,
  usePostExpellAllStudentMutation,
} from "./services/isu";
import { formNameToFormOfEducation } from "./utils/formOfEducationToFormName";

export const App: FC = () => {
  const [currentPage, setCurrentPage] = useState(0);
  const [sort, setSort] = useState<string[]>([]);

  const studyGroupsQuery = useGetStudyGroupsQuery({
    page: currentPage,
    pageSize: PAGE_SIZE,
    sort,
  });

  const maxAdminGroupQuery = useGetStudyGroupMaxAdminQuery(null);
  const minAdminGroupQuery = useGetStudyGroupMinAdminQuery(null);

  const groupsCountedByNameQuery = useGetStudyGroupsCountedByNameQuery(null);

  const [expellAll, expellAllMutation] = usePostExpellAllStudentMutation();
  const [deleteAll, deleteAllMutatation] = useDeleteStudyGroupMutation();
  const [changeForm, changeFormMutation] = usePostChangeEduFormMutation();

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

  return (
    <>
      <Styles />
      <Header />
      <MainContainer>
        <LoadContainer
          isLoading={
            studyGroupsQuery.isLoading &&
            maxAdminGroupQuery.isLoading &&
            minAdminGroupQuery.isLoading &&
            groupsCountedByNameQuery.isLoading &&
            expellAllMutation.isLoading &&
            deleteAllMutatation.isLoading &&
            changeFormMutation.isLoading
          }
        >
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
              title="Количество элементов, сгрупированных по имени"
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
    </>
  );
};
