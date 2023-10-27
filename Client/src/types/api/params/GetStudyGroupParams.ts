export interface GetStudyGroupFilters {
  name?: string;
  creationDate?: Date;
  studentsCount?: number;
  formOfEducation?: string;
  semesterEnum?: string;
}

export interface GetStudyGroupParams extends GetStudyGroupFilters {
  page?: number;
  pageSize?: number;
  sort?: Array<string>;
}
