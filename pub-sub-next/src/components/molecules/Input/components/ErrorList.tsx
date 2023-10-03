import {FC} from "react";
import styles from '../styles.module.css';
export const ErrorList: FC<{errors: string[]}> = ({errors}) => {
    
    const renderErrorItem = (item: string) => (
        <li key={item}>{item}</li>
      );

    return <ul className={styles.errors}>{errors.map(renderErrorItem)}</ul>
}