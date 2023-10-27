import { createApi } from "@reduxjs/toolkit/query/react";
import { BASE_ISU_API } from "configs";
import { Key } from "react";
import { StudyGroupFormOfEducationEnum, TagTypes } from "types";

import { studyGroupApi } from "./studyGroup";

export const isuApi = createApi({
  baseQuery: BASE_ISU_API,
  endpoints: (builder) => ({
    postChangeEduForm: builder.mutation<
      null,
      { id: Key; form: StudyGroupFormOfEducationEnum }
    >({
      invalidatesTags: [TagTypes.GROUP],
      async onQueryStarted(data, { dispatch, queryFulfilled }) {
        try {
          await queryFulfilled;
          studyGroupApi.util.resetApiState();
        } catch (e) {
          console.log(e);
        }
      },
      query: ({ form, id }) => ({
        method: "POST",
        url: `/groups/${id}/change-edu-form/${form}`,
      }),
    }),
    postExpellAllStudent: builder.mutation<null, Key>({
      invalidatesTags: [TagTypes.GROUP],
      async onQueryStarted(data, { dispatch, queryFulfilled }) {
        try {
          await queryFulfilled;
          studyGroupApi.util.resetApiState();
        } catch (e) {
          console.log(e);
        }
      },
      query: (id) => ({
        method: "POST",
        url: `/groups/${id}/expel-all`,
      }),
    }),
  }),
  reducerPath: "isu",
  tagTypes: [TagTypes.GROUP],
});

export const { usePostChangeEduFormMutation, usePostExpellAllStudentMutation } =
  isuApi;
