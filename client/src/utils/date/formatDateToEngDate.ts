import { formatWithOptions } from "date-fns/fp";
import { ru } from "date-fns/locale";

export const formatDateToEngDate = formatWithOptions(
  { locale: ru },
  "yyyy-MM-dd"
);
