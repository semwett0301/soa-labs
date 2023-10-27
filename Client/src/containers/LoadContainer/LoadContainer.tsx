import { Spin } from "antd";
import React, { FC, PropsWithChildren } from "react";

import { LoadWrapper } from "./ui/LoadWrapper";

interface Props {
  isLoading: boolean;
}

export const LoadContainer: FC<PropsWithChildren<Props>> = ({
  children,
  isLoading,
}) => <LoadWrapper>{isLoading ? <Spin size="large" /> : children}</LoadWrapper>;
