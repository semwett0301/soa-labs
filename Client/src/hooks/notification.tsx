import { notification as antdBlock } from "antd";
import { NotificationPlacement } from "antd/es/notification/interface";
import React, { useCallback, useMemo, useState } from "react";

import { NotificationContextProps } from "../context/notitcation";

export function useNotification(
  Consumer: React.Consumer<NotificationContextProps>
) {
  const [api, contextHolder] = antdBlock.useNotification();
  const [value, setValue] = useState<NotificationContextProps>({
    notification: "",
  });

  const setNotification = useCallback((notification: string) => {
    setValue({
      notification,
    });
  }, []);

  const openNotification = (placement: NotificationPlacement) => {
    api.info({
      message: <Consumer>{({ notification }) => notification}</Consumer>,
      placement,
    });
  };

  return {
    contextHolder,
    openNotification,
    setNotification,
    value,
  };
}
