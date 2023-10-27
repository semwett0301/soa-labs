import { configureStore } from "@reduxjs/toolkit";
import { studyGroupApi } from "services";

export const store = configureStore({
  middleware: (getDefaultMiddleware) =>
    getDefaultMiddleware().concat(studyGroupApi.middleware),
  reducer: {
    [studyGroupApi.reducerPath]: studyGroupApi.reducer,
  },
});
