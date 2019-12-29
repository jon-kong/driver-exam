var createModule = {
    data:{
        optionNo: 0
    },

    addDiv:function () {

        var e = document.getElementById("option0");
        var div = document.createElement("option" + createModule.data.optionNo);
        div.className = "form-inline";
        div.id = "option" + createModule.data.optionNo;

        div.innerHTML = '<label class="custom-control-label col-md-2">选项' + (createModule.data.optionNo + 1) + '</label>' +
            '<textarea class="form-control"  placeholder="选项描述" name="options[' + createModule.data.optionNo + '].name"  cols="60" rows="2" ></textarea>' +
            '<input type="checkbox" class="form-control" name = "options[' + createModule.data.optionNo + '].isAnswer" value="1" /><label>正确选项</label>' +
            '<button type="button" class="button btn-light" onclick="createModule.delDiv(' + createModule.data.optionNo + ')"> -删除选项</button>';

        document.getElementById("options").appendChild(div);

        createModule.data.optionNo++;
    },

    delDiv(no){
        var div = document.getElementById("option" + no);
        document.getElementById("options").removeChild(div);
    }

};