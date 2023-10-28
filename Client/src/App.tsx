import { Header } from "atom";
import { Styles } from "configs";
import { NotificationConsumer, NotificationProvider } from "context";
import { useNotificationConfig } from "hooks";
import React, { FC } from "react";
import { Provider } from "react-redux";
import { RouterProvider } from "react-router-dom";
import { router } from "router";
import { store } from "store";

const App: FC = () => {
  const { contextHolder, notificationValue, openNotification } =
    useNotificationConfig(NotificationConsumer);

  return (
    <Provider store={store}>
      <NotificationProvider
        value={{
          notification: notificationValue,
          openNotification,
        }}
      >
        {contextHolder}
        <Styles />
        <Header />
        <RouterProvider router={router} />
      </NotificationProvider>
    </Provider>
  );
};

export default App;
