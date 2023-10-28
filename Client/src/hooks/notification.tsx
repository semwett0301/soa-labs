import { notification as antdBlock } from "antd";
import { NotificationContextValue } from "context";
import React, { useState } from "react";

export function useNotificationConfig(
  Consumer: React.Consumer<NotificationContextValue>
) {
  const [api, contextHolder] = antdBlock.useNotification();
  const [notificationValue, setNotificationValue] = useState<string>("");

  const openNotification = (value: string) => {
    setNotificationValue(value);
    api.error({
      message: <Consumer>{({ notification }) => notification}</Consumer>,
      placement: "bottomRight",
    });
  };

  return {
    contextHolder,
    notificationValue,
    openNotification,
  };
}
