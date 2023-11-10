import React, { FC } from "react";

import { AdditionalWrapper } from "./ui/AdditionalWrapper";
import { Title } from "./ui/Title";
import { Value } from "./ui/Value";

interface Props {
  title: string;
  value?: string;
}

export const Additional: FC<Props> = ({ title, value }) => {
  return (
    <AdditionalWrapper>
      <Title>{title}</Title>
      <Value>{value ?? ""}</Value>
    </AdditionalWrapper>
  );
};
