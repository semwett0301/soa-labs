import { StudyGroupSemesterEnumEnum } from "types";

export const semesterEnumToNumber: {
  [key in StudyGroupSemesterEnumEnum]: number;
} = {
  [StudyGroupSemesterEnumEnum.THIRD]: 3,
  [StudyGroupSemesterEnumEnum.FIFTH]: 5,
  [StudyGroupSemesterEnumEnum.SIXTH]: 6,
};
