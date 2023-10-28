import { EditFilled } from "@ant-design/icons";
import { Button, Pagination, Select, Table } from "antd";
import {
  FilterValue,
  SorterResult,
  TableCurrentDataSource,
  TablePaginationConfig,
} from "antd/es/table/interface";
import { FilterDropdown } from "atom";
import { PAGE_SIZE } from "configs";
import { NotificationContext } from "context";
import { isValid, parse } from "date-fns";
import React, {
  Dispatch,
  FC,
  Key,
  useCallback,
  useContext,
  useState,
} from "react";
import { Link } from "react-router-dom";
import {
  CustomTableColumns,
  FormName,
  GetStudyGroupFilters,
  StudyGroup,
} from "types";
import {
  formNameToFormOfEducation,
  numberToSemesterEnum,
  studyGroupToColumn,
} from "utils";

interface Props {
  content?: StudyGroup[];
  totalPages?: number;
  currentPage: number;
  setCurrentPage: Dispatch<number>;
  setSort: Dispatch<string[]>;
  onDelete: (id: Key) => Promise<void>;
  onExpell: (id: Key) => Promise<void>;
  onUpdateForm: (id: Key, formName: FormName) => Promise<void>;
  onFilter: Dispatch<(prevState: GetStudyGroupFilters) => GetStudyGroupFilters>;
}

export const TableData: FC<Props> = ({
  content,
  currentPage,
  onDelete,
  onExpell,
  onFilter,
  onUpdateForm,
  setCurrentPage,
  setSort,
  totalPages,
}) => {
  const [editableRow, setEditableRow] = useState<number | null>(null);

  const { openNotification } = useContext(NotificationContext);

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

  const onChange = useCallback<
    (
      pagination: TablePaginationConfig,
      filters: Record<string, FilterValue | null>,
      sorter:
        | SorterResult<CustomTableColumns>
        | SorterResult<CustomTableColumns>[],
      extra: TableCurrentDataSource<CustomTableColumns>
    ) => void
  >(
    (pagination, filters, sorter, extra) => {
      if (Array.isArray(sorter)) {
        sorter.forEach((sortItem) => {
          sortSetter(sortItem.field as Key, sortItem.order);
        });
      } else {
        sortSetter(sorter.field as Key, sorter.order);
      }

      const resFilters = {
        ...filters,
      };

      Object.keys(resFilters).forEach((key) => {
        if (!resFilters[key]) {
          resFilters[key] = undefined;
        } else if (key === "creationDate") {
          const parsedDate = parse(
            resFilters[key][0] as unknown as string,
            "dd.MM.yyyy",
            new Date()
          );

          if (!isValid(parsedDate)) {
            const message = "Дата должна быть в формате dd.mm.YYYY";

            openNotification(message);
            resFilters[key] = undefined;
          } else {
            resFilters[key] = parsedDate.toString() as unknown as FilterValue;
          }
        } else if (key === "formOfEducation") {
          if (
            Object.values(FormName).includes(resFilters[key][0] as FormName)
          ) {
            const formName = resFilters[key][0] as FormName;
            resFilters[key] = formNameToFormOfEducation[
              formName
            ].toString() as unknown as FilterValue;
          } else {
            const message = "Значение должно совпадать с одним из вариантов";

            openNotification(message);
            resFilters[key] = undefined;
          }
        } else if (key === "semesterEnum") {
          const number = Number(resFilters[key][0] as string);

          if (number === 3 || number === 5 || number === 6) {
            resFilters[key] = numberToSemesterEnum[
              number
            ] as unknown as FilterValue;
          } else {
            openNotification("Семестр может быть 3, 5 и 6");
            resFilters[key] = undefined;
          }
        }
      });

      onFilter((prevState) => ({
        ...prevState,
        ...resFilters,
      }));
    },
    [onFilter, openNotification, sortSetter]
  );

  return (
    <>
      <Table<CustomTableColumns>
        onChange={onChange}
        pagination={false}
        dataSource={studyGroupToColumn(content ?? [])}
        columns={[
          {
            dataIndex: "id",
            ellipsis: true,
            sorter: true,
            title: "ID",
            width: 75,
          },
          {
            dataIndex: "name",
            ellipsis: true,
            filterDropdown: FilterDropdown,
            sorter: true,
            title: "Название",
            width: 140,
          },
          {
            dataIndex: "creationDate",
            ellipsis: true,
            filterDropdown: FilterDropdown,
            sorter: true,
            title: "Дата создания",
          },
          {
            dataIndex: "studentsCount",
            ellipsis: true,
            filterDropdown: FilterDropdown,
            sorter: true,
            title: "Кол-во студентов",
            width: "12%",
          },
          {
            dataIndex: "formOfEducation",
            ellipsis: true,
            filterDropdown: FilterDropdown,
            sorter: true,
            title: "Вид обучения",
          },
          {
            dataIndex: "semesterEnum",
            ellipsis: true,
            filterDropdown: FilterDropdown,
            sorter: true,
            title: "Семестр",
            width: 130,
          },
          {
            dataIndex: "groupAdminName",
            ellipsis: true,
            sorter: true,
            title: "Имя админа",
          },
          {
            key: "delete",
            render: (value, record) => (
              <Button onClick={() => onDelete(record.id)} danger size="small">
                Удалить
              </Button>
            ),
            title: (
              <Link to="/add">
                <Button>Создать</Button>
              </Link>
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
          {
            align: "center",
            key: "edit",
            render: (value, record) => (
              <Link to={`/edit/${record.id}`}>
                <EditFilled />
              </Link>
            ),
            width: 48,
          },
          {
            key: "expell",
            render: (value, record) => (
              <Button ghost onClick={() => onExpell(record.id)} size="small">
                Отчислить
              </Button>
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
