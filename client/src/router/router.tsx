import { AppPanel, GroupAddPanel, GroupEditPanel } from "panels";
import React from "react";
import { createBrowserRouter } from "react-router-dom";

export const router = createBrowserRouter([
  {
    element: <AppPanel />,
    path: "/",
  },
  {
    element: <GroupAddPanel />,
    path: "/add",
  },
  {
    element: <GroupEditPanel />,
    path: "/edit/:id",
  },
]);
