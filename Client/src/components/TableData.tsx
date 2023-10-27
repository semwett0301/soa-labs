import { Table } from "atom";
import { LoadContainer } from "containers";
import React, { FC } from "react";
import { useGetStudyGroupsQuery } from "services";
import { studyGroupToColumn } from "utils";

export const TableData: FC = () => {
  const { data, isLoading } = useGetStudyGroupsQuery({
    page: 1,
  });

  return (
    <LoadContainer isLoading={isLoading}>
      <Table items={studyGroupToColumn(data?.content ?? [])} />
    </LoadContainer>
  );
};
