import React, { FC, PropsWithChildren } from "react";

import { Title } from "./ui/Title";
import { Wrapper } from "./ui/Wrapper";

interface Props {
  title: string;
}

export const TitleWrapper: FC<PropsWithChildren<Props>> = ({
  children,
  title,
}) => (
  <Wrapper>
    <Title>{title}</Title>
    {children}
  </Wrapper>
);
