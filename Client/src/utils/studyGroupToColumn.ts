import { omit } from "ramda";
import { CustomTableColumns, StudyGroup } from "types";

import { formatDateToRussianDate } from "./date";
import { formOfEducationToString } from "./formOfEducationToString";
import { semesterEnumToNumber } from "./semesterEnumToNumber";

export const studyGroupToColumn: (
  groups: StudyGroup[]
) => CustomTableColumns[] = (groups) => {
  return groups.map((group) => {
    const resGroup = omit(
      ["formOfEducation", "groupAdmin", "semesterEnum"],
      group
    );

    return {
      ...resGroup,
      creationDate: formatDateToRussianDate(group.creationDate),
      formOfEducation: formOfEducationToString[group.formOfEducation],
      groupAdminName: group.groupAdmin.name,
      semester: semesterEnumToNumber[group.semesterEnum],
    };
  });
};
