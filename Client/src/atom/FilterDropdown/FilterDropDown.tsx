import { Input } from "antd";
import { FilterDropdownProps } from "antd/es/table/interface";
import React, { FC } from "react";

export const FilterDropdown: FC<FilterDropdownProps> = ({
  clearFilters,
  confirm,
  setSelectedKeys,
}) => (
  <Input
    allowClear
    onChange={(event) => {
      setSelectedKeys([event.target.value]);
      if (!event.target.value) clearFilters();
    }}
    onBlur={() => {
      confirm({
        closeDropdown: true,
      });
    }}
  />
);
