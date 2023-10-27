import { createApi } from "@reduxjs/toolkit/query/react";
import { BASE_GROUP_API } from "configs";
import { Key } from "react";
import {
  GroupCountByNameResponse,
  StudyGroup,
  StudyGroupsResponse,
  TagTypes,
} from "types";

import { GetStudyGroupParams } from "../types/api/params/GetStudyGroupParams";

export const studyGroupApi = createApi({
  baseQuery: BASE_GROUP_API,
  endpoints: (builder) => ({
    deleteStudyGroup: builder.mutation<Key, Key>({
      invalidatesTags: [TagTypes.GROUP],
      query: (id) => ({
        method: "DELETE",
        url: `/groups/${id}`,
      }),
    }),
    getStudyGroupMaxAdmin: builder.query<StudyGroup, null>({
      providesTags: [TagTypes.GROUP],
      query: () => ({
        method: "POST",
        url: "/groups/max-admin",
      }),
    }),
    getStudyGroupMinAdmin: builder.query<StudyGroup, null>({
      providesTags: [TagTypes.GROUP],
      query: () => ({
        method: "POST",
        url: "/groups/min-admin",
      }),
    }),
    getStudyGroups: builder.query<
      StudyGroupsResponse | undefined,
      GetStudyGroupParams
    >({
      providesTags: [TagTypes.GROUP],
      query: (params) => ({
        method: "GET",
        params,
        url: "/groups",
      }),
    }),
    getStudyGroupsCountedByName: builder.query<
      GroupCountByNameResponse[],
      null
    >({
      providesTags: [TagTypes.GROUP],
      query: () => ({
        method: "POST",
        url: "/groups/group-count-by-name",
      }),
    }),
  }),
  reducerPath: "studyGroups",
  tagTypes: [TagTypes.GROUP],
});

export const {
  useDeleteStudyGroupMutation,
  useGetStudyGroupMaxAdminQuery,
  useGetStudyGroupMinAdminQuery,
  useGetStudyGroupsCountedByNameQuery,
  useGetStudyGroupsQuery,
} = studyGroupApi;
