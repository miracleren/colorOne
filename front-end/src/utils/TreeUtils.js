/**
 * 遍历树返回节点
 * @param tree
 * @param fn
 */
export function traverseTree(tree, fn) {
    if (!tree || typeof (fn) != 'function')
        return

    for (let node of tree) {
        fn(node)
        traverseTree(node.children, fn)
    }
}

/**
 * 遍历树，返回路径
 * @param tree
 * @param fieldName
 * @param id
 * @param fn
 */
export function traverseTreePaths(tree, fieldName, id, fn) {
    if (!tree || typeof (fn) != 'function')
        return

    let paths = []
    traverseTreeDept(tree, fieldName, id, paths)
    fn(paths)
}

//深度遍历递归函数
function traverseTreeDept(tree, fieldName, id, paths) {
    if (!tree)
        return

    for (let node of tree) {
        traverseTreeDept(node.children, fieldName, id, paths)
        if (paths.length > 0 || node[fieldName] === id) {
            paths.push(node)
            break
        }
    }
}
