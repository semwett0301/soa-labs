import { Button, Pagination, Select, Table } from "antd";
import { PAGE_SIZE } from "configs";
import React, { Dispatch, FC, Key, useCallback, useState } from "react";
import { CustomTableColumns, FormName, StudyGroup } from "types";
import { studyGroupToColumn } from "utils";

interface Props {
  content?: StudyGroup[];
  totalPages?: number;
  currentPage: number;
  setCurrentPage: Dispatch<number>;
  setSort: Dispatch<string[]>;
  onDelete: (id: Key) => Promise<void>;
  onExpell: (id: Key) => Promise<void>;
  onUpdateForm: (id: Key, formName: FormName) => Promise<void>;
}

export const TableData: FC<Props> = ({
  content,
  currentPage,
  onDelete,
  onExpell,
  onUpdateForm,
  setCurrentPage,
  setSort,
  totalPages,
}) => {
  const [editableRow, setEditableRow] = useState<number | null>(null);

  const sortSetter = useCallback(
    (id: Key, order: "ascend" | "descend") => {
      let resultSort: string[] = [];

      if (order) {
        if (order === "ascend") {
          resultSort = [...resultSort, `+${id}`];
        } else {
          resultSort = [...resultSort, `-${id}`];
        }
      }
      setSort(resultSort);
    },
    [setSort]
  );

  return (
    <>
      <Table<CustomTableColumns>
        onChange={(pagination, filters, sorter) => {
          if (Array.isArray(sorter)) {
            sorter.forEach((sortItem) => {
              sortSetter(sortItem.field as Key, sortItem.order);
            });
          } else {
            sortSetter(sorter.field as Key, sorter.order);
          }
        }}
        pagination={false}
        size="small"
        dataSource={studyGroupToColumn(content ?? [])}
        columns={[
          {
            dataIndex: "id",
            ellipsis: true,
            sorter: true,
            title: "ID",
          },
          {
            dataIndex: "name",
            ellipsis: true,
            sorter: true,
            title: "Название",
          },
          {
            dataIndex: "creationDate",
            ellipsis: true,
            sorter: true,
            title: "Дата создания",
          },
          {
            dataIndex: "studentsCount",
            ellipsis: true,
            sorter: true,
            title: "Кол-во студентов",
          },
          {
            dataIndex: "formOfEducation",
            ellipsis: true,
            sorter: true,
            title: "Вид обучения",
          },
          {
            dataIndex: "semesterEnum",
            ellipsis: true,
            sorter: true,
            title: "Номер семестра",
          },
          {
            dataIndex: "groupAdminName",
            ellipsis: true,
            sorter: true,
            title: "Имя админа",
          },
          {
            key: "expell",
            render: (value, record) => (
              <Button onClick={() => onExpell(record.id)} size="small">
                Отчислить
              </Button>
            ),
          },
          {
            key: "delete",
            render: (value, record) => (
              <Button onClick={() => onDelete(record.id)} danger size="small">
                Удалить
              </Button>
            ),
          },
          {
            key: "changeEduForm",
            render: (value, record) => (
              <span>
                {record.id !== editableRow ? (
                  <Button
                    onClick={() => {
                      setEditableRow(record.id);
                    }}
                    size="small"
                  >
                    Форма
                  </Button>
                ) : (
                  <Select
                    size="small"
                    defaultValue={record.formOfEducation}
                    onChange={(newForm) => {
                      onUpdateForm(record.id, newForm);
                      setEditableRow(null);
                    }}
                    options={[
                      {
                        label: FormName.EVENING.toString(),
                        value: FormName.EVENING,
                      },
                      {
                        label: FormName.DISTANT.toString(),
                        value: FormName.DISTANT,
                      },
                      {
                        label: FormName.FULL_DAY.toString(),
                        value: FormName.FULL_DAY,
                      },
                    ]}
                  />
                )}
              </span>
            ),
          },
        ]}
        bordered
      />
      <Pagination
        current={currentPage + 1}
        total={totalPages ? totalPages * PAGE_SIZE : PAGE_SIZE}
        pageSize={PAGE_SIZE}
        responsive
        onChange={(page) => {
          setCurrentPage(page - 1);
        }}
        hideOnSinglePage
      />
    </>
  );
};
