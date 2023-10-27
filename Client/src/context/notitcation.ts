import React from "react";

export interface NotificationContextProps {
  notification: string;
}

export interface NotificationContextValue extends NotificationContextProps {
  openNotification: (notification: string) => void;
}

export const NotificationContext =
  React.createContext<NotificationContextValue>({
    notification: "",
    openNotification: (notification) => {
      console.log(notification);
    },
  });

export const NotificationConsumer = NotificationContext.Consumer;
export const NotificationProvider = NotificationContext.Provider;
