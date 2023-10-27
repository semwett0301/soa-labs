import { StudyGroupFormOfEducationEnum } from "types";

export const formOfEducationToString: {
  [key in StudyGroupFormOfEducationEnum]: string;
} = {
  [StudyGroupFormOfEducationEnum.DISTANCEEDUCATION]: "Дистанционный",
  [StudyGroupFormOfEducationEnum.EVENINGCLASSES]: "Вечерний",
  [StudyGroupFormOfEducationEnum.FULLTIMEEDUCATION]: "Полный день",
};
