package com.taffy.neko.service;

import com.taffy.neko.Result.R;
import com.taffy.neko.models.dto.UpdateUserStatusDTO;

public interface AdminService {
    R<?> articlePage(int pageNum, int pageSize, String header, String sortBy);

    R<?> deleteArticleById(String id);

    R<?> getCommentsById(int pageNum, int pageSize, String id);

    R<?> deleteCommentById(String id);

    R<?> userInfoPage(int pageNum, int pageSize, String userName, String nickName);

    R<?> updateStatus(UpdateUserStatusDTO reqDTO);
}
