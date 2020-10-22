import React, {useEffect, useState} from "react";
import axios from 'axios';
import "bootstrap/dist/css/bootstrap.min.css";
import { Link } from "react-router-dom";
import SummaryNote from "../StickyNote/SummaryNote";

function Home(){
    const [lastestNotes, setLatestNotes] = useState ([]);

    useEffect(() => {
        const getLatestNotes = async () =>{
            axios.get("http://localhost:7000/content")
        .then(response =>{
            console.log(response.data)
            setLatestNotes(response.data.latestContent)
        })
        }
        getLatestNotes();
        }, []
        
    )

    return (
        <div className="container">
          <h1>Ultimos agregados</h1>
            {lastestNotes.map(note => (
            <SummaryNote key={note.id} note={note}/>
            )
            )}
        </div>
        )
}

export default Home;