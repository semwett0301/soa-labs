import { omit } from "ramda";
import { CustomTableColumns, StudyGroup } from "types";

import { formatDateToRussianDate } from "./date";
import { formOfEducationToString } from "./formOfEducationToString";
import { semesterEnumToNumber } from "./semesterEnumToNumber";

export const studyGroupToColumn: (
  groups: StudyGroup[]
) => CustomTableColumns[] = (groups) => {
  return groups.map((group, idx) => {
    const resGroup = omit(
      ["formOfEducation", "groupAdmin", "semesterEnum"],
      group
    );

    return {
      ...resGroup,
      creationDate: formatDateToRussianDate(new Date(group.creationDate)),
      formOfEducation: formOfEducationToString[group.formOfEducation],
      groupAdminName: group.groupAdmin.name,
      key: idx,
      semesterEnum: semesterEnumToNumber[group.semesterEnum],
    };
  });
};
