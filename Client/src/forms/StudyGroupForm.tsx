import { Button, Form, Input, Select } from "antd";
import { useForm } from "antd/es/form/Form";
import { NotificationContext } from "context";
import React, { FC, useContext, useEffect } from "react";
import { useGetPersonsQuery } from "services";
import { StudyGroupRequest, StudyGroupRequestFormOfEducationEnum } from "types";
import { formOfEducationToFormName, numberToSemesterEnum } from "utils";

interface Props {
  initialValues?: StudyGroupRequest;
  onFinish: (data: StudyGroupRequest) => Promise<void> | void;
}

export const StudyGroupForm: FC<Props> = ({ initialValues, onFinish }) => {
  const [form] = useForm<StudyGroupRequest>();

  const { data, isError, isLoading } = useGetPersonsQuery(null);

  const { openNotification } = useContext(NotificationContext);

  useEffect(() => {
    if (isError) {
      openNotification("Возникла ошибка при загрузке пользователей");
    }
  }, [isError]);

  return (
    <Form
      form={form}
      name="studyGroupCreate"
      initialValues={initialValues}
      onFinish={onFinish}
      scrollToFirstError
    >
      <Form.Item
        name="name"
        label="Название"
        rules={[
          {
            message: "Укажите имя группы",
            required: true,
          },
        ]}
      >
        <Input />
      </Form.Item>
      <Form.Item
        name="studentsCount"
        label="Количество студентов"
        rules={[
          {
            message: "Укажите количество студентов",
            required: true,
          },
          {
            message: "Поддерживаются только числа > 0",
            pattern: /^\d+$/,
          },
        ]}
      >
        <Input />
      </Form.Item>
      <Form.Item
        name="formOfEducation"
        label="Вид обучения"
        rules={[
          {
            message: "Укажите вид обучения",
            required: true,
          },
        ]}
      >
        <Select
          options={[
            {
              label:
                formOfEducationToFormName[
                  StudyGroupRequestFormOfEducationEnum.DISTANCEEDUCATION
                ],
              value: StudyGroupRequestFormOfEducationEnum.DISTANCEEDUCATION,
            },
            {
              label:
                formOfEducationToFormName[
                  StudyGroupRequestFormOfEducationEnum.EVENINGCLASSES
                ],
              value: StudyGroupRequestFormOfEducationEnum.EVENINGCLASSES,
            },
            {
              label:
                formOfEducationToFormName[
                  StudyGroupRequestFormOfEducationEnum.FULLTIMEEDUCATION
                ],
              value: StudyGroupRequestFormOfEducationEnum.FULLTIMEEDUCATION,
            },
          ]}
        />
      </Form.Item>
      <Form.Item
        name="semesterEnum"
        label="Текущий семестр"
        rules={[
          {
            message: "Укажите текущий семестр",
            required: true,
          },
        ]}
      >
        <Select
          options={[
            {
              label: 3,
              value: numberToSemesterEnum[3],
            },
            {
              label: 5,
              value: numberToSemesterEnum[5],
            },
            {
              label: 5,
              value: numberToSemesterEnum[6],
            },
          ]}
        />
      </Form.Item>
      <Form.Item
        name="groupAdmin"
        label="Админ"
        rules={[
          {
            message: "Укажите текущий семестр",
            required: true,
          },
        ]}
      >
        <Select
          loading={isLoading || isError}
          disabled={isLoading || isError}
          options={
            data?.content
              ? data.content.map((person) => ({
                  label: person.name,
                  value: person,
                }))
              : []
          }
        />
      </Form.Item>
      <Form.Item wrapperCol={{ offset: 8, span: 16 }}>
        <Button size="large" type="primary" htmlType="submit">
          {initialValues ? "Обновить" : "Создать"}
        </Button>
      </Form.Item>
    </Form>
  );
};
