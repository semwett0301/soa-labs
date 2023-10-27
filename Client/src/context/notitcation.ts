import React from "react";

export interface NotificationContextProps {
  notification: string;
}

export const NotificationContext =
  React.createContext<NotificationContextProps>({
    notification: "",
  });

export const NotificationConsumer = NotificationContext.Consumer;
export const NotificationProvider = NotificationContext.Provider;
