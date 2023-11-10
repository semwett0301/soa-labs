import { FormSection, LoadContainer, TitleWrapper } from "containers";
import { NotificationContext } from "context";
import { StudyGroupForm } from "forms";
import React, { FC, useCallback, useContext, useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";
import {
  useGetStudyGroupByIdQuery,
  usePostStudyGroupMutation,
  usePutStudyGroupMutation,
} from "services";
import { StudyGroupRequest } from "types";

export const GroupEditPanel: FC = () => {
  const navigate = useNavigate();
  const { openNotification } = useContext(NotificationContext);
  const { id } = useParams();

  const studyGroupByIdQuery = useGetStudyGroupByIdQuery(id);
  const [putStudyGroup, putStudyGroupMutation] = usePutStudyGroupMutation();

  const onFinish = useCallback<(data: StudyGroupRequest) => Promise<void>>(
    async (data) => {
      await putStudyGroup({
        ...data,
        id,
      });

      navigate("/");
    },
    [id, navigate, putStudyGroup]
  );

  useEffect(() => {
    if (putStudyGroupMutation.isError) {
      openNotification("При редактировании группы произошла ошибка");
    }
  }, [putStudyGroupMutation.isError]);

  return (
    <FormSection>
      <TitleWrapper title="Редактирование группы">
        <LoadContainer isLoading={studyGroupByIdQuery.isLoading}>
          <StudyGroupForm
            initialValues={studyGroupByIdQuery.data}
            onFinish={onFinish}
          />
        </LoadContainer>
      </TitleWrapper>
    </FormSection>
  );
};
