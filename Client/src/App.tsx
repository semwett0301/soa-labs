import { Header } from "atom/Header";
import { Styles } from "configs/styles";
import React, { FC } from "react";

export const App: FC = () => {
  return (
    <>
      <Styles />
      <Header />
    </>
  );
};
