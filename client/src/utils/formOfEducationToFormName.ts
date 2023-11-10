import { FormName, StudyGroupFormOfEducationEnum } from "types";

export const formOfEducationToFormName: {
  [key in StudyGroupFormOfEducationEnum]: FormName;
} = {
  [StudyGroupFormOfEducationEnum.DISTANCEEDUCATION]: FormName.DISTANT,
  [StudyGroupFormOfEducationEnum.EVENINGCLASSES]: FormName.EVENING,
  [StudyGroupFormOfEducationEnum.FULLTIMEEDUCATION]: FormName.FULL_DAY,
};

export const formNameToFormOfEducation: {
  [key in FormName]: StudyGroupFormOfEducationEnum;
} = {
  [FormName.DISTANT]: StudyGroupFormOfEducationEnum.DISTANCEEDUCATION,
  [FormName.EVENING]: StudyGroupFormOfEducationEnum.EVENINGCLASSES,
  [FormName.FULL_DAY]: StudyGroupFormOfEducationEnum.FULLTIMEEDUCATION,
};
