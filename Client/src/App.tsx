import { Header } from "atom/Header";
import { TableData } from "components";
import { Styles } from "configs/styles";
import { MainContainer, TableSection } from "containers";
import React, { FC } from "react";
import { Provider } from "react-redux";
import { store } from "store";

export const App: FC = () => {
  return (
    <Provider store={store}>
      <Styles />
      <Header />
      <MainContainer>
        <TableSection>
          <TableData />
        </TableSection>
      </MainContainer>
    </Provider>
  );
};
