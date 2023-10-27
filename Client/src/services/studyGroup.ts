import { createApi } from "@reduxjs/toolkit/query/react";
import { BASE_GROUP_API } from "configs";
import { StudyGroupsResponse } from "types";

import { GetStudyGroupParams } from "../types/api/params/GetStudyGroupParams";

export const studyGroupApi = createApi({
  baseQuery: BASE_GROUP_API,
  endpoints: (builder) => ({
    getStudyGroups: builder.query<
      StudyGroupsResponse | undefined,
      GetStudyGroupParams
    >({
      query: (params) => ({
        method: "GET",
        params,
        url: "/groups",
      }),
    }),
  }),
  reducerPath: "studyGroups",
});

export const { useGetStudyGroupsQuery } = studyGroupApi;
