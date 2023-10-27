export type GetStudyGroupParams = {
  page?: number;
  pageSize?: number;
  name?: string;
  creationDateFrom?: Date;
  creationDateTo?: Date;
  studentsCount?: number;
  formOfEducation?: string;
  semesterEnum?: string;
  sort?: Array<string>;
};
