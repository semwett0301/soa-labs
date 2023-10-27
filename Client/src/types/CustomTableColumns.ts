import { StudyGroup } from "./api";

export interface CustomTableColumns
  extends Omit<
    StudyGroup,
    "formOfEducation" | "groupAdmin" | "semesterEnum" | "creationDate"
  > {
  formOfEducation?: string;
  groupAdminName?: string;
  semester?: number;
  creationDate?: string;
}
