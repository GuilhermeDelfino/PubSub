import {FC, PropsWithChildren} from "react";
import styles from '../styles.module.css'
export const ErrorItem: FC<{error: string} & PropsWithChildren> = ({error, children}) => {
    return (
        <li className={styles.error_item}>
          {children}
        </li>
    )
}