import React, {useEffect, useState} from "react";

import {axios} from '../../boot/axios'
import {useHistory, useParams} from 'react-router-dom';


function Note(){

    let { noteId } = useParams();
    let history = useHistory();

    const [note, setNote] = useState ({id: noteId, title: null, author: null, categories: []})

    useEffect(() => {
        axios.get('/content' + noteId)
            .then(success =>{
                console.log(success.data)
                setNote(success.data)
            })
            .catch(error => {
                history.push("/404");
            })
    }, [history, noteId]
    )

    return(
        <div>
            <div className="contenido">
                <div className="titleAndDescription">
                    <h1 className="title">{note.title}</h1>
                    <div className="author">{note.author}</div>
                    <div className="categories">{note.categories} </div>
                </div>
            </div>

        </div>
    )
}