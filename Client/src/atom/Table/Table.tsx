import { Table as AntdTable } from "antd";
import { ColumnsType } from "antd/es/table";
import React, { FC } from "react";
import { CustomTableColumns } from "types";

const columns: ColumnsType<CustomTableColumns> = [
  {
    key: "id",
    title: "ID",
  },
  {
    key: "name",
    title: "Название",
  },
  {
    key: "creationDate",
    title: "Дата создания",
  },
  {
    key: "studentsCount",
    title: "Кол-во студентов",
  },
  {
    key: "formOfEducation",
    title: "Вид обучения",
  },
  {
    key: "semester",
    title: "Номер семестра",
  },
  {
    key: "groupAdminName",
    title: "Имя админа",
  },
];

interface Props {
  items: CustomTableColumns[];
}

export const Table: FC<Props> = ({ items }) => {
  return (
    <AntdTable<CustomTableColumns>
      size="small"
      columns={columns}
      dataSource={items}
    />
  );
};
