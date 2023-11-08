import { fetchBaseQuery } from "@reduxjs/toolkit/query/react";

export const BASE_URL = "https://localhost:443/api/v1"

export const BASE_GROUP_API = fetchBaseQuery({
  baseUrl: BASE_URL,
});

export const BASE_ISU_API = fetchBaseQuery({
  baseUrl: `${BASE_URL}/isu`,
});
