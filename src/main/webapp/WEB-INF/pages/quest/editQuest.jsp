<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/pages/components/header.jsp" %>

<div class="container-md">
    <form class="form-horizontal needs-validation"
          action="${rootPath}${s.editQuest}?${s.paramId}=${param.id}" method="post"
          enctype="multipart/form-data" novalidate>
        <div class="row justify-content-md-center">

            <div class="col col-md-4">
                <div class="card align-items-center mx-auto my-3 quest-creator">
                    <div class="my-custom-scrollbar mb-0 w-100">
                        <div class="col-md-9 mx-auto">

                            <!-- Form Name -->
                            <p class="fw-normal fs-4 my-3 mx-auto">
                                ${requestScope.quest.id > 0
                                        ? "Edit quest"
                                        : langQuestCreateLegend}
                            </p>

                            <!-- Name input-->
                            <div class="form-group">
                                <label class="control-label mb-2" for="questName">${langQuestName}</label>
                                <input id="questName" name="${s.paramName}" type="text"
                                       placeholder="${langQuestNamePlaceholder}"
                                       class="form-control input-md" required
                                       value="${requestScope.quest.name}">
                            </div>

                            <!-- Text input-->
                            <div class="form-group">
                                <label class="control-label my-2" for="questText">Paste the quest content</label>
                                <textarea class="form-control" required style="min-height: 62px"
                                          id="questText"
                                          placeholder="pattern must be..."
                                          name="text">${requestScope.quest.text}</textarea>
                            </div>

                            <!--Image input-->
                            <div class="form-group">
                                <label class="control-label my-2" for="questImage">${langQuestImage}</label>
                                <input id="questImage" name="${s.inputImage}" type="file" class="form-control input-md">
                            </div>

                            <!-- Button -->
                            <div class="form-group text-center">
                                <button class="btn btn-outline-secondary mx-auto my-4" id="submit"
                                        name="${requestScope.quest.id > 0 ? s.updateBtn : s.createBtn}">
                                    ${requestScope.quest.id > 0 ? langUpdateBtn : langCreateBtn}
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col col-md-5">
                <div class="card my-3 quest-creator">
                    <div class="my-custom-scrollbar mb-0 w-100">
                        <p class="fw-normal fs-4 my-3 text-center">Add quest content (optional)</p>
                        <div class="col-md-10 mx-auto">
                            <!-- Text area-->
                            <label class="control-label mb-2" for="questPattern">Paste the quest content</label>

                            <textarea class="form-control" style="min-height: 200px"
                                      id="questPattern"
                                      name="content"
                                      placeholder="pattern must be..."></textarea>


                            <!--File input-->
                            <div class="form-group">
                                <label class="control-label my-2" for="twineFile">or Load twine file</label>
                                <input id="twineFile" name="twine" type="file"
                                       class="form-control input-md mb-3">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
<%@ include file="/WEB-INF/pages/components/footer.jsp" %>