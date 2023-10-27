import { fetchBaseQuery } from "@reduxjs/toolkit/query/react";

export const BASE_GROUP_API = fetchBaseQuery({
  baseUrl: "http://localhost:8080/api/v1",
});
