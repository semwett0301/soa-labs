import { createApi } from "@reduxjs/toolkit/query/react";
import { BASE_GROUP_API } from "configs";
import { Key } from "react";
import {
  GetStudyGroupParams,
  GroupCountByNameResponse,
  Person,
  PutRequest,
  StudyGroup,
  StudyGroupRequest,
  StudyGroupsResponse,
  TagTypes,
} from "types";

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
    getPersons: builder.query<Person[] | undefined, null>({
      query: () => ({
        method: "GET",
        url: "/persons",
      }),
    }),
    getStudyGroupById: builder.query<StudyGroup, string>({
      providesTags: [TagTypes.GROUP],
      query: (id) => ({
        method: "GET",
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
    postStudyGroup: builder.mutation<StudyGroup, StudyGroupRequest>({
      invalidatesTags: [TagTypes.GROUP],
      query: (body) => ({
        body,
        method: "POST",
        url: "/groups",
      }),
    }),
    putStudyGroup: builder.mutation<null, PutRequest<StudyGroupRequest>>({
      invalidatesTags: [TagTypes.GROUP],
      query: (body) => ({
        body,
        method: "PUT",
        url: `/groups/${body.id}`,
      }),
    }),
  }),
  reducerPath: "studyGroups",
  tagTypes: [TagTypes.GROUP],
});

export const {
  useDeleteStudyGroupMutation,
  useGetPersonsQuery,
  useGetStudyGroupByIdQuery,
  useGetStudyGroupMaxAdminQuery,
  useGetStudyGroupMinAdminQuery,
  useGetStudyGroupsCountedByNameQuery,
  useGetStudyGroupsQuery,
  usePostStudyGroupMutation,
  usePutStudyGroupMutation,
} = studyGroupApi;
