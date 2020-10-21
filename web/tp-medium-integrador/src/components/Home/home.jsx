import React, {useEffect, useState} from "react";
import axios from 'axios';
import "bootstrap/dist/css/bootstrap.min.css";

function Home(){
    const [lastestNotes, setLatestNotes] = useState ([]);

    useEffect(() => {
        const getLatestNotes = async () =>{
            axios.get("http://localhost:7000/content")
        .then(response =>{
            console.log(response.data)
            setLatestNotes(response.data)
        })
        }
        getLatestNotes();
        }, []
        
    )

    return (
        <div className="container">
           <ul>
            {lastestNotes.map(todo => (
                <li key={todo.id}>
                    {todo.title}
                </li>
            ))}
            </ul> 
        </div>
        )
}

export default Home;