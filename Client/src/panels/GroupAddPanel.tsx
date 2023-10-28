import { FormSection, LoadContainer, TitleWrapper } from "containers";
import { NotificationContext } from "context";
import { StudyGroupForm } from "forms";
import React, { FC, useCallback, useContext, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { usePostStudyGroupMutation } from "services";
import { StudyGroupRequest } from "types";

export const GroupAddPanel: FC = () => {
  const navigate = useNavigate();
  const { openNotification } = useContext(NotificationContext);

  const [postGroup, postGroupMutation] = usePostStudyGroupMutation();

  const onFinish = useCallback<(data: StudyGroupRequest) => Promise<void>>(
    async (data) => {
      await postGroup(data);
      navigate("/");
    },
    [navigate, postGroup]
  );

  useEffect(() => {
    if (postGroupMutation.isError) {
      openNotification("При создании группы произошла ошибка");
    }
  }, [postGroupMutation.isError]);

  return (
    <FormSection>
      <TitleWrapper title="Создание группы">
        <LoadContainer isLoading={postGroupMutation.isLoading}>
          <StudyGroupForm onFinish={onFinish} />
        </LoadContainer>
      </TitleWrapper>
    </FormSection>
  );
};
