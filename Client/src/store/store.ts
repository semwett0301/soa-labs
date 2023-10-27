import { configureStore } from "@reduxjs/toolkit";
import { studyGroupApi } from "services";

import { isuApi } from "../services/isu";

export const store = configureStore({
  middleware: (getDefaultMiddleware) =>
    getDefaultMiddleware()
      .concat(studyGroupApi.middleware)
      .concat(isuApi.middleware),
  reducer: {
    [studyGroupApi.reducerPath]: studyGroupApi.reducer,
    [isuApi.reducerPath]: isuApi.reducer,
  },
});
