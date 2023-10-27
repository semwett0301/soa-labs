import { StudyGroupSemesterEnumEnum } from "types";

export const semesterEnumToNumber: {
  [key in StudyGroupSemesterEnumEnum]: number;
} = {
  [StudyGroupSemesterEnumEnum.THIRD]: 3,
  [StudyGroupSemesterEnumEnum.FIFTH]: 5,
  [StudyGroupSemesterEnumEnum.SIXTH]: 6,
};

export const numberToSemesterEnum: {
  [key: number]: StudyGroupSemesterEnumEnum;
} = {
  3: StudyGroupSemesterEnumEnum.THIRD,
  5: StudyGroupSemesterEnumEnum.FIFTH,
  6: StudyGroupSemesterEnumEnum.SIXTH,
};
