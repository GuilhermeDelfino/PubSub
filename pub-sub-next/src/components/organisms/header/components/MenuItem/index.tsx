import Link from "next/link";
import { FC } from "react";
import styles from "./styles.module.css";
export const MenuItem: FC<{ item: string[] }> = ({ item }) => {
  return (
    <li className={styles.menu_item} key={item[0]}>
      <Link href={item[1]} className={styles.link}>
        {item[0]}
      </Link>
    </li>
  );
};
