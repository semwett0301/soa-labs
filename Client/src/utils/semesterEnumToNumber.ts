import { StudyGroupSemesterEnum } from "types";

export const semesterEnumToNumber: {
  [key in StudyGroupSemesterEnum]: number;
} = {
  [StudyGroupSemesterEnum.THIRD]: 3,
  [StudyGroupSemesterEnum.FIFTH]: 5,
  [StudyGroupSemesterEnum.SIXTH]: 6,
};

export const numberToSemesterEnum: {
  [key: number]: StudyGroupSemesterEnum;
} = {
  3: StudyGroupSemesterEnum.THIRD,
  5: StudyGroupSemesterEnum.FIFTH,
  6: StudyGroupSemesterEnum.SIXTH,
};
