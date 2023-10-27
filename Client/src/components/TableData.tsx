import { Pagination, Table } from "antd";
import { PAGE_SIZE } from "configs";
import { LoadContainer } from "containers";
import React, { FC, Key, useCallback, useState } from "react";
import { useGetStudyGroupsQuery } from "services";
import { CustomTableColumns } from "types";
import { studyGroupToColumn } from "utils";

export const TableData: FC = () => {
  const [currentPage, setCurrentPage] = useState(0);
  const [sort, setSort] = useState<string[]>([]);

  const { data, isLoading } = useGetStudyGroupsQuery({
    page: currentPage,
    pageSize: PAGE_SIZE,
    sort,
  });

  const sortSetter = useCallback((id: Key, order: "ascend" | "descend") => {
    setSort(() => {
      let resultSort: string[] = [];

      if (order) {
        if (order === "ascend") {
          resultSort = [...resultSort, `+${id}`];
        } else {
          resultSort = [...resultSort, `-${id}`];
        }
      }

      return resultSort;
    });
  }, []);

  return (
    <LoadContainer isLoading={isLoading}>
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
        dataSource={studyGroupToColumn(data?.content ?? [])}
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
        ]}
      />
      <Pagination
        current={currentPage + 1}
        total={data?.totalPages ? data.totalPages * PAGE_SIZE : PAGE_SIZE}
        pageSize={PAGE_SIZE}
        responsive
        onChange={(page) => {
          setCurrentPage(page - 1);
        }}
        hideOnSinglePage
      />
    </LoadContainer>
  );
};
