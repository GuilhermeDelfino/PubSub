import strings from "@/util/strings";
import { MenuItem } from "../MenuItem";
import { FC } from "react";

const { HOME, RECENT_EVENTS, PUBLISH_EVENT, SUBSCRIBE } = strings.header;
const menuStrings = [HOME, RECENT_EVENTS, PUBLISH_EVENT, SUBSCRIBE];

export const MenuItems: FC = () =>
  menuStrings.map((item) => <MenuItem key={item[0]} item={item} />);
