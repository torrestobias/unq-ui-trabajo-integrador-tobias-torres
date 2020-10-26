import React, {useEffect, useState} from "react";
import Comment from '../Comment/Comment';
import {axios} from '../../boot/axios'
import {useHistory, useParams} from 'react-router-dom';
import Navbar from "../Navbar/Navbar";
import "../../styles/note.css";


function Note(){

    let { id } = useParams();

    const [note, setNote] = useState ({title: "", author: "", body: "", categories: [], comments : []})

    useEffect(() => {
        axios.get("http://localhost:7000/content/"+id)
            .then(success =>{
                console.log(success.data)
                setNote(success.data)
            })
            .catch(error =>
                console.log(error)
                )            
    }, []
    );

    return(
        <div className="todo">
            
            <Navbar/>
                <div className="titleAndDescription">
                    <h1 >{note.title}</h1>
                    <div className="text-note">Author: {note.author.name}</div>
                    <div className="text-note">{note.body}</div>
                    <div className="text-note">Categories: {note.categories} </div>
                    <div className="text-note">
                        <h3>Comentaries: </h3>
                        {note.comments.map(commentary =>(
                            <Comment key={commentary.id} commentary={commentary}/>
                        ))}
                    </div>
                    
                </div>

           

        </div>
    )
}

export default Note;
