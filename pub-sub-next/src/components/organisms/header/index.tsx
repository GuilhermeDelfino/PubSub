import { FC } from "react";
import styles from "./styles.module.css";
import { MenuItems } from "./components/MenuItems";

export const Header: FC = () => {
  return (
    <header className={styles.header}>
      <ul className={styles.menu}>
        <MenuItems />
      </ul>
    </header>
  );
};
