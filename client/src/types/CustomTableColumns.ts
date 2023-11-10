import { StudyGroup } from "./api";
import { FormName } from "./FormName";

export interface CustomTableColumns
  extends Omit<
    StudyGroup,
    "formOfEducation" | "groupAdmin" | "semesterEnum" | "creationDate"
  > {
  formOfEducation?: FormName;
  groupAdminName?: string;
  semesterEnum?: number;
  creationDate?: string;
  key: number;
}
