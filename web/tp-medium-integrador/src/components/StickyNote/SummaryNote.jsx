import React from 'react';


function SummaryNote ({note}){
    
    return(
        <div>
        <p className="text">{note.title}</p>
        <p className="text">{note.author.name}</p>
        <p className="text">{note.categories}</p>
        <button type="button" class="btn btn-outline-secondary">Entrar a Nota</button>
        </div>
        )
}

export default SummaryNote;