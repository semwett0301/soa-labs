export type PutRequest<T extends object> = T & {
  id: string;
};
